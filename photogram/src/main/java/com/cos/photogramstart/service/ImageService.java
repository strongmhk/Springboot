package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> 인기사진(){
        return imageRepository.mPopular();
    }


    @Transactional(readOnly = true) // 영속성 컨텍스트 변경 감지를 해서, 더티 체킹, flush(반영) -> readOnly true시 flush를 하지않음
    public Page<Image> 이미지스토리(int principalId, Pageable pageable){
        Page<Image> images = imageRepository.mStory(principalId, pageable);

        // 2(ssar) 로그인
        // images에 좋아요 상태 담기
        images.forEach((image)->{

            image.setLikeCount(image.getLikes().size());

            image.getLikes().forEach((like) -> {
                if(like.getUser().getId() == principalId){ // 해당 이미지에 좋아요한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한 것인지 비교
                    image.setLikeState(true);
                }
            });

        });


        return images;
    }



    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID(); // 중복이 안되는 고유성을 가진 숫자 생성
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename(); // + 1.jpg

        Path imageFilePath = Paths.get(uploadFolder + imageFileName); // C:/project/Springboot/upload/1.jpg

        // 통신, 디스크 I/O -> 예외가 발생할 수 있다.(실제 디스크에는 해당 파일명을 가진 파일이 없다던지)
        try{
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }

        // image 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); //
        imageRepository.save(image);

//        System.out.println(imageEntity.toString());
    }

}
