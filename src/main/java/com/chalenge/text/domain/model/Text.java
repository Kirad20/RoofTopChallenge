package com.chalenge.text.domain.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.vladmihalcea.hibernate.type.json.JsonType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "text")
@SQLDelete(sql = "UPDATE text SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Text {
  private @Id @GeneratedValue Long id;
  private String hash;

  @Min(value = 2, message = "Text must be at least 2 characters long")
  @Builder.Default
  private int chars = 2;
  @Builder.Default
  private Boolean deleted = Boolean.FALSE;

  @Type(type = "json")
  @Column(columnDefinition = "json")
  private Map<String, Integer> result;

  
}
