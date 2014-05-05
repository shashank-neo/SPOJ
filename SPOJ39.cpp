#include<iostream>
using namespace std;
int main(){
	int t;
	cin>>t;
	while(t--){
	  int E, F, N, minVal, minValIndex;
	  cin>>E>>F;
	  int tempArr[F-E+1];
	  cin>>N;
	  int coins[N][2];
	  for(int i=0;i<N;i++){
	    cin>>coins[i][0]>>coins[i][1];
	  }
	  for(int i=0;i<F-E+1;i++) tempArr[i]=-1;
	  tempArr[0] = 0;
	  for(int i=1;i<F-E+1;i++){
	    minVal = 2147483647;
	    minValIndex = -1;
	    for(int k=0;k<N;k++){
	      if(i-coins[k][1]>=0){
	        if(tempArr[i-coins[k][1]]>=0){
	          if(minVal>tempArr[i-coins[k][1]] + coins[k][0]){
	            minVal = tempArr[i-coins[k][1]] + coins[k][0];
	            minValIndex = k;
	          }
	        }
	      }
	    }
	    if(minValIndex!=-1)
	    tempArr[i] = tempArr[i-coins[minValIndex][1]] + coins[minValIndex][0];

		//cout<<"tempArr after loop: "<< i<< "\n";
		//for(int j=0;j<F-E+1;j++) cout<<tempArr[j]<<" ";
	  }
	  if(tempArr[F-E]!=-1) cout<<"The minimum amount of money in the piggy-bank is "<<tempArr[F-E]<<".\n";
	  else cout<<"This is impossible.\n";
	}
}