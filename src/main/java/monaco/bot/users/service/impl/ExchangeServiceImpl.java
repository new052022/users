package monaco.bot.users.service.impl;

import lombok.RequiredArgsConstructor;
import monaco.bot.users.model.Exchange;
import monaco.bot.users.repository.ExchangeRepository;
import monaco.bot.users.service.interfaces.ExchangeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository exchangeRepository;

    @Override
    public Exchange getExchangeByName(String name) {
        return exchangeRepository.getExchangeByName(name).orElseThrow(() ->
                new NoSuchElementException(String.format("Exchange with name %s doesn't exist", name)));
    }

    @Override
    public List<Exchange> getAll() {
        return exchangeRepository.findAll();
    }

}
