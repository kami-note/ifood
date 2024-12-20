package com.kryprforge.ui.screens;

import com.kryprforge.context.GlobalInfos;
import com.kryprforge.models.PaymentMethod;
import com.kryprforge.dao.PaymentMethodDAO;
import com.kryprforge.ui.utils.CLIUtils;
import com.kryprforge.ui.components.InputField;
import com.kryprforge.ui.components.Table;
import org.fusesource.jansi.Ansi;

import java.util.List;

public class PaymentMethodScreen {

    private final PaymentMethodDAO paymentMethodDAO;
    private final GlobalInfos globalInfos;

    public PaymentMethodScreen(PaymentMethodDAO paymentMethodDAO, GlobalInfos globalInfos) {
        this.paymentMethodDAO = paymentMethodDAO;
        this.globalInfos = globalInfos;
    }

    public void render() {
        List<PaymentMethod> paymentMethods = paymentMethodDAO.findAll();

        if (paymentMethods.isEmpty()) {
            System.out.println(CLIUtils.color("Nenhuma forma de pagamento encontrada.", Ansi.Color.YELLOW));
        } else {
            System.out.println(CLIUtils.color("Formas de pagamento cadastradas:", Ansi.Color.CYAN));

            String[] headers = {"ID", "Nome"};

            List<String[]> rows = paymentMethods.stream()
                    .map(paymentMethod -> new String[]{
                            String.valueOf(paymentMethod.getId()),
                            paymentMethod.getPaymentType() != null ? paymentMethod.getPaymentType() : "N/A",
                    })
                    .toList();

            Table table = new Table(headers, rows);
            table.render();

            InputField<Long> idField = new InputField<>("Digite o ID do método de pagamento a ser selecionado:", Long::parseLong, "ID inválido!");
            Long selectedId = idField.getUserInput();

            PaymentMethod selectedPaymentMethod = paymentMethodDAO.findById(selectedId);
            if (selectedPaymentMethod != null) {
                globalInfos.setPaymentMethod(selectedPaymentMethod.getId());
                System.out.println(CLIUtils.color("Método de pagamento selecionado com sucesso!", Ansi.Color.GREEN));
            } else {
                System.out.println(CLIUtils.color("Método de pagamento não encontrado!", Ansi.Color.RED));
            }
        }
    }
}
