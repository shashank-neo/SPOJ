#include<iostream>
using namespace std;
int main(){
	int budget, numOfParty;
	cin>>budget>>numOfParty;
	while(budget>0 && numOfParty>0){
		int arr[101][501] = {0};
		int parties[numOfParty+1][2];
		for(int i=1;i<numOfParty+1;i++){
			cin>>parties[i][0]>>parties[i][1];
		}
		//for(int i=0;i<budget+1;i++) arr[0][i]=0;
		//for(int i=0;i<numOfParty+1;i++) arr[0][i]=0;
		for(int i=1;i<numOfParty+1;i++){
			for(int j=1;j<budget+1;j++){
				if(j<parties[i][0]) {
					arr[i][j] = arr[i-1][j];
				} else {
					arr[i][j] = max(arr[i-1][j], arr[i-1][j-parties[i][0]] + parties[i][1]);
				}
			}
		}
		int k;
		for(k=budget;k>0;k--){
			if(arr[numOfParty][k]!=arr[numOfParty][k-1]) break;
		}
		cout<<k<<" "<<arr[numOfParty][k]<<"\n";
		cin>>budget>>numOfParty;
	}
}