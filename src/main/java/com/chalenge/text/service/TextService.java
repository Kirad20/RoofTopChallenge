package com.chalenge.text.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.chalenge.text.domain.model.Text;
import com.chalenge.text.domain.repository.TextRepository;
import com.chalenge.text.domain.response.TextNotFoundException;
import com.chalenge.text.util.Md5Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TextService {
  @Autowired
  TextRepository textRepository;

  public Page<Text> findByCharsAndPaginate(int chars, int rpp, int page) {
    chars = chars < 2 ? 2 : chars;
    rpp = rpp < 10 ? 10 : rpp;
    rpp = rpp > 100 ? 100 : rpp;
    
    return textRepository.findByChars(chars, PageRequest.of(page, rpp));
  }
  

  public Text create(String value,Integer chars) {
    chars = chars < 2 ? 2 : chars;
    Text newText = Text.builder()
      .hash(Md5Utils.getMD5EncryptedValue(value))
      .chars(chars)
      .result(this.generateResJson(chars, value))
      .build();
    return textRepository.save(newText);
  }

  public Text findOrCreate(String value, int chars) {
    String hash = Md5Utils.getMD5EncryptedValue(value);
    Optional<Text> exisText =textRepository.findByHashAndChars(hash, chars);
    if(exisText.isPresent()) {
      return exisText.get();
    }else {
      return this.create(value, chars);
    }
  }

  public Text findByHashAndChars(String value, int chars) {
    chars = chars < 2 ? 2 : chars;
    return textRepository.findByHashAndChars(Md5Utils.getMD5EncryptedValue(value), chars)
    .orElseThrow(() -> new TextNotFoundException());
  }

  public Text findById(Long id){
    return textRepository.findById(id).orElseThrow(() -> new TextNotFoundException());
  }

  public void delete(Long id){
    textRepository.findById(id).orElseThrow(() -> new TextNotFoundException());
    textRepository.deleteById(id);
  }

  private Map<String,Integer> generateResJson(int chars, String value){
    if (chars>value.length()) {
      Map<String,Integer> result = new HashMap<>();
      result.put(value, 1);
      return result;
    } 
    
    value = value.toLowerCase();
    Map<String,Integer> result = new HashMap<>();
    for(int i=0; i<value.length(); i++){
      if ((i+chars) <= value.length()) {
        StringBuilder key = new StringBuilder();
        for (int j = 0; j < chars; j++) {
          key.append(value.charAt(i+j));
        }
        if(result.containsKey(key.toString())){
          result.put(key.toString(), result.get(key.toString())+1);
        }else{
          result.put(key.toString(), 1);
        }
      }
    }
    return result;
  }
  
}
