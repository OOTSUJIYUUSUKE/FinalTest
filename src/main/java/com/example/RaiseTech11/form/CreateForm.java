package com.example.RaiseTech11.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateForm {
    @NotBlank
    @Length(min = 1, max = 100)
    private String name;
    @NotBlank
    @Pattern(regexp = "^[0-9]{8}$")  //yyyyMMddの形式で表示
    private String birthday;

}
