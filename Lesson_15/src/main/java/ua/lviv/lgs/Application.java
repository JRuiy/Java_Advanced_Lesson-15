package ua.lviv.lgs;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Application {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		configuration.configure("/META-INF/hibernate.cfg.xml");
		
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		
		Post post = new Post();
		post.setTitle("Gabar");
		
		Comment comment = new Comment();
		comment.setAuthorName("Taras");
		comment.setPost(post);
		
		Comment comment2 = new Comment();
		comment2.setAuthorName("Vasia");
		comment2.setPost(post);
		
		Set<Comment> comments = new HashSet<Comment>();
		comments.add(comment);
		comments.add(comment2);
		
		post.setComments(comments);
		
//		Save to DB
		
		Transaction transaction = session.beginTransaction();
		session.save(post);
		transaction.commit();
		
//		Read from DB
		Post postDB = (Post) session.get(Post.class, 1);
		System.out.println(postDB + "---->" + postDB.getComments());
		
		Comment commentDB = (Comment) session.get(Comment.class, 2);
		System.out.println(commentDB + "---->" + commentDB.getPost());
	}
	
	
	
}
