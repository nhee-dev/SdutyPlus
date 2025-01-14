package com.d205.sdutyplus.domain.timer.controller;

import com.d205.sdutyplus.global.response.ResponseDto;
import com.d205.sdutyplus.util.TimeFormatter;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.d205.sdutyplus.global.response.ResponseCode.GET_LOCALDATETIME_SUCCESS;

@RestController
@RequestMapping("/timer")
public class TimerController {

    @ApiOperation(value = "서버 현재 시간 조회")
    @GetMapping("")
    public ResponseEntity<ResponseDto> getServerTime(){
        LocalDateTime datetime = TimeFormatter.getTodayDateTime();
        String now = TimeFormatter.LocalDateTimeToString(datetime);
        return ResponseEntity.ok().body(ResponseDto.of(GET_LOCALDATETIME_SUCCESS, now));
    }
}
