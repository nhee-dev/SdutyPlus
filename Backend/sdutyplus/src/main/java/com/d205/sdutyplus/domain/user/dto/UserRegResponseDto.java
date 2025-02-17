package com.d205.sdutyplus.domain.user.dto;

import com.d205.sdutyplus.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRegResponseDto {
    private Long userSeq;
    private String email;
    private String nickname;
    private String jobName;
    private String imgUrl;
    private String fcmToken;

    public UserRegResponseDto(User user){
        this.userSeq = user.getSeq();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.jobName = user.getJob().getJobName();
        this.imgUrl = user.getImgUrl();
        this.fcmToken = user.getFcmToken();
    }
}

