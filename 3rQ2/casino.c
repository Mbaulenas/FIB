#include <iostream>
#include <map>
#include <string>

using namespace std;

int main(){

    map <string, int> casino;
    map<string, int>::iterator it;

    int x;

    string nom;
    string accio;


    while(cin>>nom>>accio){

        if(accio=="enters"){

            it = casino.find(nom);

            if(it==casino.end() ) casino.insert(pair<string,int> (nom, 0));

            else cout << nom << " is already in the casino" << endl;
        }

        else if(accio=="wins"){

            cin >> x;

            it = casino.find(nom);

            if(it==casino.end() ) cout << nom << " is not in the casino" << endl;

            else it->second += x;

        }

        else if(accio=="leaves"){

            it = casino.find(nom);

            if(it==casino.end() ) cout << nom << " is not in the casino" << endl;

            else{

                cout << it->first << " has won " << it->second << endl;

                casino.erase(it);

            }
        }


    }


    it=casino.begin();

    cout << "----------" << endl;

    while(it!=casino.end()){

        cout << it->first << " is winning " << it->second << endl;
        ++it;
    }

}
