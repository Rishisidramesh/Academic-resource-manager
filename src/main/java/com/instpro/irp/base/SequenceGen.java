package com.instpro.irp.base;

public class SequenceGen extends BaseService{ 
    private String sequence_name;
    private long starts_from;
    private int incrementby;
    Interfaces.IncrementDelegate delincrementfunction;
    private String prefix;
    private String suffix;
    private long currentvalue;
    public String nextval(){
        if(!(isnull(delincrementfunction))){
            starts_from=delincrementfunction.fnIncrement(starts_from);
        }
        else{
            starts_from+=nvl(incrementby,1);
        }
      currentvalue=starts_from;
        return nvl(prefix,BlankString()) +String.valueOf(starts_from)+ nvl(suffix,BlankString());
    }
    public String currentval(){
        if(isnull(currentvalue)){
            return BlankString();
        }
        return nvl(prefix,BlankString()) +String.valueOf(currentvalue)+ nvl(suffix,BlankString());
    }

        public SequenceGen(String psname,
                       long pstartfrom,
                       int pincby,
                       String ppref,
                       String psuff,
                       Interfaces.IncrementDelegate pfndelegate) {

        this.sequence_name = psname;
        this.starts_from = pstartfrom;
        this.incrementby = pincby;
        this.prefix = ppref;
        this.suffix = psuff;
        if(!(isnull(pfndelegate))){
             this.delincrementfunction = pfndelegate;
        }

    }
}

