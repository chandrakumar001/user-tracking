package json.exampleView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExampleView {
/*
    @Autowired
    ExampleViewRep exampleViewRep;

    @Autowired
    PartCVViewTodoRepository1 todoView;


    @GetMapping("/exampleView")
    public List<TodoView> test() {
//
//        Page<TodoView> all = todoView.findAll(PageRequest.of(0, 8));
//        return all.getContent();
        return todoView.findAll();
    }

    @GetMapping("/exampleView1")
    public List<Example> test1() {

        Page<Example> all = exampleViewRep.findAll(PageRequest.of(0, 2));
        return all.getContent();
    }

}

@Repository
interface ExampleViewRep extends JpaRepository<Example, Integer> {
}

@Repository
interface PartCVViewTodoRepository1 extends JpaRepository<TodoView, String> {

    @Query(value = "select * from views.todo_view", nativeQuery = true)
    List<TodoView> findAll();*/
}