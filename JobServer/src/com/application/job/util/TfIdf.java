package com.application.job.util;

import java.util.List;

import com.application.job.model.pojo.JobSkills;

public class TfIdf{
	
	public static int tfCalculator(List<JobSkills> skills, String skill)
	{
		int count = 0;
		
		for(JobSkills skillFound : skills)
		{
			if(skillFound.getSkill().equalsIgnoreCase(skill))
			{
				count++;
			}
		}
		return count;
	}
	
	public static double idfCalculcator(List<List<JobSkills>> jobs, String skill)
	{
		double count = 0;
		
		for(List<JobSkills> skills : jobs)
		{
			for(JobSkills skillFound : skills)
			{
				if(skillFound.getSkill().equalsIgnoreCase(skill))
				{
					count++;
					break;
				}
			}
		}
		
		return 1 + Math.log(jobs.size()/count);
	}
	
	public static double tfIdfCalculator(String skill, List<JobSkills> skills, List<List<JobSkills>> jobs)
	{
		double tf, idf, tfIdf;
		
		tf = TfIdf.tfCalculator(skills, skill);
		idf = TfIdf.idfCalculcator(jobs, skill);
		tfIdf = tf*idf;
		
		return tfIdf;
	}
}
