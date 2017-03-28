package it.extrasys.studio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="BookShelf")
public class ShelfEntity extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name="shelfCode", unique = true)
	private String code;

	public ShelfEntity() {

	}

	public ShelfEntity(String code){
	    this.code = code;
	}

	public String getCode(){
	    return code;
	}

	public void setCode(String code){
	    this.code = code;
	}

	@Override
	public String toString(){
	    return super.getId() + "-" + code;
	}
}
