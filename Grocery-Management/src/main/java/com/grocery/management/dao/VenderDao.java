package com.grocery.management.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grocery.management.entity.Vender;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class VenderDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Boolean isAlreadyExits(String email) {
		try {
			Query query = entityManager.createQuery("from Vender v where v.email = :email and v.isDeleted =false");
			query.setParameter("email", email);
			query.getSingleResult();
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public Object save(Vender vender) {
		return entityManager.merge(vender);
	}

	public Object getById(String id) {
		try {
			Query query = entityManager.createQuery("from Vender v where v.id = :id and v.isDeleted = false");
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Vender> getAll() {
		try {
			Query query = entityManager.createQuery("from Vender v where v.isDeleted = false");
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public Object deleteById(String id) {
		try {
			Query query = entityManager.createQuery("update Vender v set v.isDeleted = true where v.id = :id");
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
