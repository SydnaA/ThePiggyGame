/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thepiggame;

/**
 *
 * @author SydnaAgnehs
 */
public class AiSystem {
    private int aiPoints;
    private int huPoints;
    private int aiAcumPoints;
    public AiSystem() {
        aiPoints=0;
        huPoints=0;
        aiAcumPoints=0;
    }
    
    public void setHuPoints(int pt) {
        huPoints=pt;
    }
    public int getAiPoints() {
        return aiPoints;
    }
    public void setAiPoints(int pt) {
        aiPoints=pt;
    }
    
    public void setAIAcumPoints(int pt) {
        aiAcumPoints=pt;
    }
    
    public void resetAIAcumPoints() {
        aiAcumPoints=0;
    }
    
    public boolean actTurn() {
        
        //System.out.println("AI POINTS: "+aiPoints+"AI ACUM PT: "+aiAcumPoints+"HU POINTS: "+huPoints);
         if(aiAcumPoints==0||aiAcumPoints<15) {
            return true;
        }
        
        
        if(aiPoints>huPoints&&aiAcumPoints>10) {
            return false;
        }
        
       
        if(aiPoints==0&&aiAcumPoints==0) {
            return true;
        }
        
        if(aiAcumPoints+aiPoints>=100) {
            return false;
        }
        
        
        if(aiAcumPoints>25) {
            return false;
        }
        
        
        if(aiPoints+aiAcumPoints<=huPoints) {
            return true;
        }
        
        if(aiPoints==0&&aiAcumPoints==0) {
            return true;
        }
        
        if(aiPoints+aiAcumPoints>90) {
            return true;
        }
        
        
        
        return false;
    }
}
