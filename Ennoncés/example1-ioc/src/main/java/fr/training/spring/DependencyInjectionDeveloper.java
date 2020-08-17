package fr.training.spring;

public class DependencyInjectionDeveloper {

	private Task task;

	public DependencyInjectionDeveloper(Task documentingTask) {
		this.task = documentingTask;
	}
	
	public void doTask() {
		task.execute();
	}
	
}
