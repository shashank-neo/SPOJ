#include<iostream>
using namespace std;

int main(){
	int numColors;
	while(cin>>numColors) {
		int colors[numColors][numColors], smoke[numColors][numColors];
		for(int i=0;i<numColors;i++){
			cin>>colors[i][i];
			smoke[i][i]=0;
		}
		for(int layer=1;layer<numColors;layer++){
			for(int k=0,i=0,j=layer;k<numColors-layer;k++,i++,j++){
				int minSmoke = 2147483647;
				int col = -1;
				for(int l=1; l<=layer; l++){
					int tmpSmoke = smoke[i][j-l] + smoke[i+layer+1-l][j] + colors[i][j-l]*colors[i+layer+1-l][j];
					if(minSmoke> tmpSmoke){
						minSmoke = tmpSmoke;
						col = (colors[i][j-l] +colors[i+layer+1-l][j])%100;
					}
				}
				smoke[i][j] = minSmoke;
				colors[i][j] = col;
			}
		}
		cout<<smoke[0][numColors-1]<<"\n";
	}
}