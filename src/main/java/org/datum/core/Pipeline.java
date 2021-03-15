package org.datum.core;

import java.util.ArrayList;
import java.util.List;

import org.datum.datasource.processors.Processor;

public class Pipeline<T> {

	// final consumer of the data
	private DataConsumer<T> dataConsumer;
	private final List<Processor<T>> processors = new ArrayList<>();

	private Pipeline() {

	}

	public void addProcessor(Processor<T> processor) {
		processors.add(processor);
	}

	public void setDataConsumer(DataConsumer<T> dataConsumer) {
		this.dataConsumer = dataConsumer;
	}

	public void process(T record) {
		for (Processor<T> proc : processors) {
			record = proc.proceed(record);
		}
		dataConsumer.insert(record);
	}

	public static class Builder<T> {

		private Pipeline<T> pipeline = null;

		public Builder() {
			pipeline = new Pipeline<>();
		}

		public Builder<T> addProcessor(Processor<T> processor) {
			pipeline.addProcessor(processor);
			return this;
		}

		public Builder<T> addConsumer(DataConsumer<T> dataConsumer) {
			pipeline.setDataConsumer(dataConsumer);
			return this;
		}

		public Pipeline<T> build() {
			return pipeline;
		}

	}

}
