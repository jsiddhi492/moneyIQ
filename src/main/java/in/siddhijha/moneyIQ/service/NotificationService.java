package in.siddhijha.moneyIQ.service;

import in.siddhijha.moneyIQ.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final ProfileRepository profileRepository;
    private final EmailService emailService;
    private final ExpenseService expenseService;
    @Value("${money.manager.frontend.url}")
    private String frontendUrl;

}
