package ua.dp.skillsup.concurrency;

public class AvgCalculator {

  public double getAvg(double[] arr){
    double sum = 0;
    for (double value : arr) {
      sum += value;
    }
    return sum / arr.length;
  }

  /**
   * Calculates average values by splitting the work between manually created threads
   * @param arr array of values
   * @param nJobs number of threads to use for calculation
   * @return average of all values
   */
  public double getAvgConcurrent(double[] arr, int nJobs){
    return 0;
  }

  /**
   * Calculates average values by splitting the work between threads of thread pool
   * @param arr array of values
   * @param nJobs number of threads to use in thread pool
   * @param nSplits number of splits to create from array
   * @return average of all values
   */
  public double getAvgThreadPool(double[] arr, int nJobs, int nSplits){
    return 0;
  }

}
