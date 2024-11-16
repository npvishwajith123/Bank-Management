package com.np.bank.management.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAdmin {
    public interface View {
        interface BasicView{}
        interface DetailedView extends BasicView{}
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.DetailedView.class)
    private int adminId;
    @JsonView(View.BasicView.class)
    private String name;
    @JsonView(View.DetailedView.class)
    private String email;
    @JsonView(View.BasicView.class)
    private String phone;


}
