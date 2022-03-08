package com.chalenge.text.api;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.validation.Valid;

import com.chalenge.text.domain.dto.TextDto;
import com.chalenge.text.domain.model.Text;
import com.chalenge.text.service.TextService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/text")
public class TextController {
  
  @Autowired
  private TextService textService;

  @GetMapping
  public Stream<Text> index(
    @RequestParam(defaultValue ="2",required = false) int chars,
    @RequestParam(defaultValue ="10",required = false) int rpp,
    @RequestParam(defaultValue ="1",required = false) int page) {
      Page<Text> textList =textService.findByCharsAndPaginate(chars, rpp, page);
      return textList.get();    
  }

  @PostMapping
  public Map<String,Object> store(@Valid @RequestBody TextDto textDto) 
  {
   
      Map<String,Object> result = new HashMap<>();
      Text text = textService.findOrCreate(textDto.getValue(), textDto.getChars()); 
      result.put("id", text.getId());
      result.put("hash", text.getHash());
      result.put("url", "/text/"+text.getId());
      return result;
   
    
  }
  
  @GetMapping("{id}")
  public Text show(@PathVariable("id") long id) {
    return textService.findById(id);
  }

  @DeleteMapping("{id}")
  public Map<String, Object> delete(@PathVariable("id") long id) {
      textService.delete(id);
      return new HashMap<>();
  }

  
}
