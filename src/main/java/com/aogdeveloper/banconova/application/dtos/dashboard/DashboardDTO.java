package com.aogdeveloper.banconova.application.dtos.dashboard;

import com.aogdeveloper.banconova.application.dtos.account.AccountSummaryDTO;
import com.aogdeveloper.banconova.application.dtos.creditcard.CreditCardSummaryDTO;
import com.aogdeveloper.banconova.application.dtos.customer.CustomerDTO;
import com.aogdeveloper.banconova.application.dtos.transaction.TransactionDTO;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private CustomerDTO customer;
    private List<AccountSummaryDTO> accounts;
    private List<CreditCardSummaryDTO> creditCards;
    private List<TransactionDTO> recentTransactions;
    private BigDecimal totalBalance;
    private int accountCount;
    private int beneficiaryCount;
}
