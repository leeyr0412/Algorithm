#include <iostream>
#include <math.h>
using namespace std;

bool isPrime(int n){
    if(n<2)
        return false;
    int s=sqrt(n);
    for(int j=2;j<=s;j++){
        if(n%j==0){
            return false;
        }
    }
    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n,m;
    cin>>n;
    int sum;
    while(n>0){
        m=2*n;
        n++;
        sum=0;
        for(int i=n;i<=m;i++){
            if(isPrime(i))
                sum++;
        }
        cout<<sum<<"\n";
        cin>>n;
    }
    return 0;
}