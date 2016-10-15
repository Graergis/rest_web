package rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rest.entity.Document;
import rest.repository.DocumentRepository;

@Controller
public class SearchController {

	@Autowired
	public DocumentRepository documentRepository;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("comment") String comment, @RequestParam("author") String author,
			@RequestParam("name") String name) {
		List<Document> list = documentRepository.findByNameContainingAndCommentContainingAndAuthorContaining(name,
				comment, author);
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("lists", list);
		return model;

	}
}

interface PersonRepository extends Repository<Document, Long> {
	List<Document> findByLastname(String lastname);
}