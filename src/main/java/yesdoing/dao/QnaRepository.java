package yesdoing.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import yesdoing.domain.Qna;

@Repository
public class QnaRepository {
	ArrayList<Qna> qnas = new ArrayList<>();
	public void insert(Qna qna) {
		qnas.add(qna);
	}
	
	public ArrayList<Qna> getQnas() {
		return qnas;
	}
	
	public Qna getQna(int index) {
		return qnas.get(index);
	}
}
