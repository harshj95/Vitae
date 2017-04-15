package com.application.job.util;

import java.util.List;

import com.application.job.model.entity.Job;
import com.application.job.model.entity.Skill;

public class TfIdf{
	
	public static int tfCalculator(List<Skill> skills, String skill)
	{
		int count = 0;
		
		for(Skill skillFound : skills)
		{
			if(skillFound.getSkillName().equalsIgnoreCase(skill))
			{
				count++;
			}
		}
		return count;
	}
	
	public static double idfCalculcator(List<Job> jobs, String skill)
	{
		double count = 0;
		
		for(Job job : jobs)
		{
			for(Skill skillFound : job.getSkills())
			{
				if(skillFound.getSkillName().equalsIgnoreCase(skill))
				{
					count++;
					break;
				}
			}
		}
		
		return 1 + Math.log(jobs.size()/count);
	}
	
	public static double tfIdfCalculator(String skill, List<Skill> skills, List<Job> jobs)
	{
		double tf, idf, tfIdf;
		
		tf = TfIdf.tfCalculator(skills, skill);
		idf = TfIdf.idfCalculcator(jobs, skill);
		tfIdf = tf*idf;
		
		return tfIdf;
	}
}
