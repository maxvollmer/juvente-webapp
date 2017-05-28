package de.juvente.backend.security

import org.springframework.data.mongodb.repository.MongoRepository
import java.io.Serializable
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Example
import java.util.List
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import de.juvente.backend.security.SecurityCheck
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface JuventeAccessSecuredRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
	
	@SecurityCheck
	override <S extends T> List<S> save(Iterable<S> entites);
	
	@SecurityCheck
	override <S extends T> S save(S entity);
	
	
	
	@SecurityCheck
	override findAll();

	@SecurityCheck
	override findAll(Sort sort);

	@SecurityCheck
	override <S extends T> List<S> findAll(Example<S> example);

	@SecurityCheck
	override <S extends T> List<S> findAll(Example<S> example, Sort sort);

	@SecurityCheck
	override Iterable<T> findAll(Iterable<ID> ids);

	@SecurityCheck
	override Page<T> findAll(Pageable pageable);

	@SecurityCheck
	override <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);
	
	
	
	@SecurityCheck
	override T findOne(ID id);
	
	@SecurityCheck
	override <S extends T> S findOne(Example<S> example);
	
	
	
	@SecurityCheck
	override <S extends T> S insert(S entity);
	
	@SecurityCheck
	override <S extends T> List<S> insert(Iterable<S> entities);
	
	
	
	
	
	@SecurityCheck
	override boolean exists(ID id);
	
	@SecurityCheck
	override <S extends T> boolean exists(Example<S> example);
	
	
	
	@SecurityCheck
	override long count();
	
	@SecurityCheck
	override <S extends T> long count(Example<S> example);
	
	
	
	@SecurityCheck
	override void delete(ID id);
	
	@SecurityCheck
	override void delete(T entity);
	
	@SecurityCheck
	override void delete(Iterable<? extends T> entities);
	
	@SecurityCheck
	override void deleteAll();
}
