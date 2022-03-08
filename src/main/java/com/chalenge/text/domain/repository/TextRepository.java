package com.chalenge.text.domain.repository;

import java.util.Optional;

import com.chalenge.text.domain.model.Text;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TextRepository extends CrudRepository<Text, Long> {
  /**
   * Finds all texts with the given number of chars.
   * @param chars
   * @param pageable
   * @return
   */
  public Page<Text> findByChars(Integer chars, Pageable pageable);

  /**
   * Find a text by its hash and chars
   * @param hash
   * @param chars
   * @return
   */
  public Optional<Text> findByHashAndChars(String hash, Integer chars);
}
