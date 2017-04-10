package com.application.job.util;

import java.util.Comparator;

import com.application.job.model.pojo.JobModel;

public class JobCompare implements Comparator<JobModel>{
	
	public int compare(JobModel j1, JobModel j2) {
	    if (j1.getFactor() > j2.getFactor()) {
	        return -1;
	    } else if (j2.getFactor() < j2.getFactor()) {
	        return 1;
	    }
	    return 0;
	}
}
