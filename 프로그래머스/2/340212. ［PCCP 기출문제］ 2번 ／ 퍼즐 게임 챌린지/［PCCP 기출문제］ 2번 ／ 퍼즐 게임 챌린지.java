class Solution {
    /* 시초 */
//     public int solution(int[] diffs, int[] times, long limit) {
//         int level; //숙련도
//         int timePrev = 0; //이전 퍼즐 소요 시간
        
//         for (level = 1; level <= 100_000; level++) {
//             long totalTime = 0; //총 소요시간
//             int i;
//             for (i = 0; i < diffs.length; i++) {
//                 int timeCur = times[i]; //현재 퍼즐의 소요 시간
                
//                 if (diffs[i] <= level) {
//                     totalTime += timeCur;
//                 } else {
//                     totalTime += (diffs[i] - level) * (timeCur + timePrev) + timeCur;
//                 }
                
//                 if (totalTime > limit) {
//                     break;
//                 }
                
//                 timePrev = timeCur;
//             }
            
//             if (i == diffs.length) {
//                 break;
//             }
//         }
        
        
//         return level;
//     }
    
    public int solution(int[] diffs, int[] times, long limit) {
        return binarySearch(1, 100_000, diffs, times, limit);
    }
    
    public int binarySearch(int min, int max, int[] diffs, int[] times, long limit) {
        if (max == min) {
            return min;
        }
        
        int mid = (min + max) / 2;
        
        if (isTotalTimeOver(diffs, times, limit, mid)) {
            return binarySearch(mid + 1, max, diffs, times, limit);
        } else {
            return binarySearch(min, mid, diffs, times, limit);
        }
    }
    
    public boolean isTotalTimeOver(int[] diffs, int[] times, long limit, int level) { //true: totalTime > limit
        int timePrev = 0;
        long totalTime = 0; //총 소요시간
        int i;
        for (i = 0; i < diffs.length; i++) {
            int timeCur = times[i]; //현재 퍼즐의 소요 시간

            if (diffs[i] <= level) {
                totalTime += timeCur;
            } else {
                totalTime += (diffs[i] - level) * (timeCur + timePrev) + timeCur;
            }

            if (totalTime > limit) {
                return true;
            }

            timePrev = timeCur;
        }

        return false;
    }
}