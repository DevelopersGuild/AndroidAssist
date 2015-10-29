package com.assist.dg.androidassist;

/**
 * Created by hendryjoe on 10/10/15.
 */
public class RequiredClass {

    private String formalClassName = "Default Class";
    private RequiredClass[] prerequisites = null;
    private float units = 0.0f;
    private boolean taken = false;
    private boolean qualified = true;
    private String univEquivalent = "Default Equiv";

    public RequiredClass(){
    }

    public RequiredClass(String name, RequiredClass[] prereqs, float numUnits, 
                          boolean take, String univEq)
    { 
       formalClassName = name;
       prerequisites = prereqs;
       units = numUnits;
       taken = take;
      univEquivalent = univEq;
      qualif();
    }


   private void qualif(){
       for (RequiredClass prerequisite : prerequisites) {
           if (!prerequisite.isTaken()) {
               qualified = false;
           }
       }
     qualified =  true;
   }

    public void setFormalClassName(String formalClassName){
        this.formalClassName = formalClassName;
    }

    public void setPrerequisites(RequiredClass[] prerequisites){
        this.prerequisites = prerequisites;
    }

    public void setUnits(float units){
        this.units = units;
    }

    public void setTaken(boolean taken){
        this.taken = taken;
    }

    public void setQualified(boolean qualified){
        this.qualified = qualified;
    }

    public void setUnivEquivalent(String univEquivalent){
        this.univEquivalent = univEquivalent;
    }

    public RequiredClass[] getPrerequisites(){
        return prerequisites;
    }

    public float getUnits(){
        return units;
    }

    public boolean isTaken(){
        return taken;
    }

    public boolean isQualified(){
        return qualified;
    }

    public String getUnivEquivalent(){
        return univEquivalent;
    }

    public String getFormalClassName(){
        return formalClassName;
    }


}
