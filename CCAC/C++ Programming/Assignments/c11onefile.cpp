#include <iostream>
using std::cout;
using std::endl;

namespace fns {
    void hello();
}

namespace gns {
    void hello();
}


int main() {
    {
        using namespace fns;
        hello();
    }
    
    {
        using namespace gns;
        hello();
    }    
    
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;
}//ends main




namespace fns {
    void hello() {
        cout << "hello from f" << endl;
    }// ends hello
}//ends namespace fns

namespace gns {
    void hello() {
        cout << "hello from g" << endl;
    }// ends hello
}//ends namespace fns
