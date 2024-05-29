package com.group.laxyapp.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCntDto {
    private long totalUserCnt;
    private long totalAdminCnt;
    private long totalL5Cnt;
    private long totalL4Cnt;
    private long totalL3Cnt;
    private long totalL2Cnt;
    private long totalL1Cnt;
}
