package aplicativo;

import dominio.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Programa {

	public static void main(String[] args) {
		
		removePessoa(2);

	}
	
	public static void initDB() {
		
		Pessoa p1 = new Pessoa("Carlos Machado", "carlos@example.com");
		Pessoa p2 = new Pessoa("Maria Alencar do Santos", "maria.santos@example.com");
		Pessoa p3 = new Pessoa("Sílvio Tolesco", "silvio@example.com");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit();
		
		System.out.println("O banco de dados foi inicializado.");
		
		em.close();
		emf.close();
		
	}
	
	public static void removePessoa(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		Pessoa p = em.find(Pessoa.class, id); // To EntityManager works with monitored entities is necessary find the reference of the object.
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		
		System.out.println("A pessoa " + p.getNome() + " foi achada e removida.");
		
		em.close();
		emf.close();
		
	}

}
