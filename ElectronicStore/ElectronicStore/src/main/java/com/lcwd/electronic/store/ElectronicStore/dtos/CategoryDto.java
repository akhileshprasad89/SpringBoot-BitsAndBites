package com.lcwd.electronic.store.ElectronicStore.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String categoryId;
    @NotBlank
    @Min(value = 4,message = "Title must be four character")
    private String title;
    @NotBlank(message = "Description is required")
    private  String description;
    private String coverImage;
}
