package monaco.bot.users.mapper;

import monaco.bot.users.dto.NewUserExchangeRequestDto;
import monaco.bot.users.dto.UserExchangeResponseDto;
import monaco.bot.users.model.Exchange;
import monaco.bot.users.model.UserExchangeInfo;
import monaco.bot.users.model.UserInfo;
import monaco.bot.users.service.interfaces.ExchangeService;
import monaco.bot.users.service.interfaces.UserInfoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserExchangeInfoMapper {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ExchangeService exchangeService;

    @Mapping(target = "userInfo", source = "userId", qualifiedByName = "toUserInfo")
    @Mapping(target = "exchange", source = "exchangeName", qualifiedByName = "toExchange")
    public abstract UserExchangeInfo toExchangeInfo(NewUserExchangeRequestDto dto);

    @Named("toUserInfo")
    public UserInfo toUserInfo(Long userId) {
        return userInfoService.getUserById(userId);
    }

    @Named("toExchange")
    public Exchange toUserInfo(String name) {
        return exchangeService.getExchangeByName(name);
    }

    @Mapping(target = "userId", source = "userInfo.id")
    @Mapping(target = "exchangeName", source = "savedExchangeInfo.exchange.name")
    public abstract UserExchangeResponseDto toExchangeInfoResponseDto(UserExchangeInfo savedExchangeInfo);

    public abstract List<UserExchangeResponseDto> toExchangeInfoResponseList(List<UserExchangeInfo> exchanges);

}
