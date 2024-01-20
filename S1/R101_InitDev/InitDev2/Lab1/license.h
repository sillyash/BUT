// HB/CB november 2022 - version for students
// License class, to handle drivers licensesses
// license.h file
#ifndef _LICENSE_H_
#define _LICENSE_H_

#include <string>
using namespace std;


const int MAXP = 12;    // Maximum number of points for a licecce
const int MINP = 0;     //  Minimum number of points


class License {
  string _name;      //  Name of the license holder (surname, also called last name, but name here to simplify things)
  string _firstName;   //  First name of the license holder (also called given name)
  string _date;     //  Date of issue of the license, format JJ/MM/YYYY (ex: 09/10/2021)
  string _number;   //  License number (ex: 0123456789)
  int _nbpoints;    //  Number of points of the license, between MINP and MAXP

 public:

  /*------------- ~License();
    Destructor
  */
  ~License();

 /*-------------  License (string, string, string, string, int); Constructor on the basis of given information
   Constructs a license from the name and first name of the holder,
   the date of issue, the number, and the number of points
 */
  License (string name="LASTNAME", string firstName="Firstname", string date="01/01/1900", string number="000000", int points=MAXP);

  /* ----------- void display() const;
     Display of a license
     Displays all data associated with the target license
  */
  void display() const;

  /*------------- int getNbpoints() const;
    Number of points of a license
    Returns the number of points of the target license
   */
  int getNbpoints() const;

   /*--------------  string getName() const;
    Name of a license holder
    Returns the name of the target license holder
   */
   string getName() const;

   /*----------- void withdrawPoints(int);
    Withdraws points from a license
    Withdraws the number of points passed as parameter from the target license,
    for a minimum of MINP.
   */
   void withdrawPoints(int);

   /*----------- void incrementPoints(int);
    Add a point to a license
    Exception if points are already MAXP
   */
   void incrementPoints();

   /*----------- void decrementPoints(int);
    Substracts a point from a license
    Exception if points are already MINP
   */
   void decrementPoints();

   bool compare(License l2) const;

};


void addPoints(License&, int);


#endif


