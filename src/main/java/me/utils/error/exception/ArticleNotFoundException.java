package me.utils.error.exception;

import me.utils.error.ErrorCode;

public class ArticleNotFoundException extends NotFoundException{
  public ArticleNotFoundException(){
    super(ErrorCode.ARTICLE_NOT_FOUND);
  }

}
