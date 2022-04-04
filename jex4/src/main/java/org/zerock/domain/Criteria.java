package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;
	private int amount;
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum= pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {}: type.split("");
	}
	
	
	/*get 방식의 적합한 url 인코딩된 결과로 만들어 줌
	 * ex) ?pageNum=3&amount=20&type=TC&keyword=%EC%83%88%EB%A1%9C
	 *     값을 return해줌
	 * */
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
}
