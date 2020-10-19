package ps.CodingTest.Coupang20201009;


public class _02 {
	public static void main(String[] args) {
		String[] customers = {"02/28 23:59:00 03","03/01 00:00:00 02", "03/01 00:05:00 01"};
		System.out.println(solution(2, customers));
	}
	
	static class Kiosk{
		boolean enable;
		int lastDate;
		int count;
		public Kiosk(boolean enable, int lastDate, int count) {
			super();
			this.enable = enable;
			this.lastDate = lastDate;
			this.count = count;
		}
	}
	
	static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	static int solution(int n, String[] customers) {
		for(int i=1;i<days.length;i++) {
			days[i] += days[i-1]; 
		}
		
		Kiosk[] kiosks = new Kiosk[n];
		for(int i=0;i<n;i++){
			kiosks[i] = new Kiosk(true, 0, 0);
		}
		
		for(int idx=0;idx<customers.length;idx++) {
			String[] splitted = customers[idx].split(" ");
			
			boolean allWorking = true;
			int maxRestTime = -1;
			int maxKio = 0;
			int minFinishTime = Integer.MAX_VALUE;
			int minKio = 0;
			
			int getTime = toSecond(splitted[0], splitted[1]);
			System.out.println(getTime);
			for(int i=0;i<n;i++) {
				if(getTime >= kiosks[i].lastDate) {
					kiosks[i].enable = true;
				}
			}
			
			for(int i=0;i<n;i++) {
				if(!kiosks[i].enable && allWorking) {
					if(minFinishTime > kiosks[i].lastDate) {
						minFinishTime = kiosks[i].lastDate;
						minKio = i;
					}
				}else {
					allWorking = false;
					int restTime = getTime - kiosks[i].lastDate;
					if(maxRestTime < restTime) {
						maxRestTime = restTime;
						maxKio = i;
					}
				}
			}
			
			if(allWorking) {
				kiosks[minKio].enable = false;
				kiosks[minKio].lastDate = getTime + (Integer.parseInt(splitted[2]) * 60);
				kiosks[minKio].count++;
			}else {
				kiosks[maxKio].enable = false;
				kiosks[maxKio].lastDate = getTime + (Integer.parseInt(splitted[2]) * 60);
				kiosks[maxKio].count++;
			}
		}
		
		int answer = -1;
		for(int i=0;i<n;i++) {
			answer = Math.max(answer, kiosks[i].count); 
		}
		return answer;
	}
	
	static int toSecond(String date, String time) {
		String[] dateSplit = date.split("/");
		
		int month = Integer.parseInt(dateSplit[0]);
		int day = Integer.parseInt(dateSplit[1]);
		
		String[] timeSplit = time.split(":");
		
		int hour = Integer.parseInt(timeSplit[0]);
		int minute = Integer.parseInt(timeSplit[1]);
		int second = Integer.parseInt(timeSplit[2]);
		
		return (days[month - 1] + day - 1) * 24 * 60 * 60 + (hour * 60 + minute) * 60 + second;
	}
}
