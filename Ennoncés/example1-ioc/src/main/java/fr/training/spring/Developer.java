package fr.training.spring;

import org.springframework.stereotype.Component;

@Component
public class Developer {

	private Task documentingTask;

	public Developer(Task documentingTask) {
		this.documentingTask = documentingTask;
	}

	public void doTask() {
		documentingTask.execute();
	}
	
}
