package com.hbf.hy;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hbf.hyBase.MemberBase;

@Entity
@Table(name="hbf_member")
public class Member extends MemberBase {
	private static final long serialVersionUID = 1L;


}
