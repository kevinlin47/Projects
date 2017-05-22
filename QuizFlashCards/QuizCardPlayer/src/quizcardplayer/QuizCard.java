/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizcardplayer;

/**
 *
 * @author Kevin Lin
 */
public class QuizCard {
    
    private String question;
    private String answer;
    
    QuizCard(String q,String a)
    {
        question=q;
        answer=a;
    }
    
    public String getQuestion()
    {
        return question;
    }
    
    public String getAnswer()
    {
        return answer;
    }
    
}
