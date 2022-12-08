package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



public class Contoller
{

    public Integer index()
    {
        return (int) (1000 + Math.random() * 1000);
    }
}
