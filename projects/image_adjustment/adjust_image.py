from bmp import *

class ImageProcesse:

#method 1
    def __init__(self,filename):
       self.pixelGrid = ReadBMP(filename)
       self.height = len(self.pixelGrid)
       self.width = len(self.pixelGrid[0])
       self.filename = filename

#method 2
    def save(self,newName):
        WriteBMP(self.pixelGrid,newName + ".bmp")

#method 3
    def invert(self):
        for h in range (self.height):
            for w in range (self.width):
                self.pixelGrid[h][w]= [
                                        255-self.pixelGrid[h][w][0], #red index 0
                                        255-self.pixelGrid[h][w][1], #green index 1
                                        255-self.pixelGrid[h][w][2], #blue index 2
                                    ]
        #WriteBMP(self.pixelGrid,"reversed-" +self.filename)

#method 4
    def displayChannel(self, channel):
        choice = input (""""
Choose between r,g,b:
[r] Red
[g] Green
[b] Blue:""")
        if choice == 'r':
            for h in range (self.height):
                for w in range (self.width):
                    self.pixelGrid[h][w]= [
                                self.pixelGrid[h][w][0], #red index 0
                                0, #green index 1
                                0, #blue index 2
                            ]
        elif choice == 'g':
            for h in range (self.height):
                for w in range (self.width):
                    self.pixelGrid[h][w]= [
                                0, #red index 0
                                self.pixelGrid[h][w][1], #green index 1
                                0, #blue index 2
                            ]
        elif choice == 'b':
            for h in range (self.height):
                for w in range (self.width):
                    self.pixelGrid[h][w]= [
                                0, #red index 0
                                0, #green index 1
                                self.pixelGrid[h][w][2], #blue index 2
                            ]
        else:
            print("Invalid choice")
            return
         #WriteBMP(self.pixelGrid,choice+"-" +self.filename)

#method 5
    def flip(self,axis):
        fliptext = input("flipped vertically or horizontally? choose between v or h:")
        if fliptext == 'v':
            return self.pixelGrid.reverse()
        elif fliptext== 'h':
            for i in range (self.height):
                if i < self.height:
                    self.pixelGrid[i].reverse()
                    i+1
        else:
            print ("Invalid choice, try again")

#method 6
    def grayscale(self):
        for h in range (self.height):
            for w in range (self.width):
                newValue = (self.pixelGrid[h][w][0]+self.pixelGrid[h][w][1]+self.pixelGrid[h][w][2])//3
                self.pixelGrid[h][w]= [
                                        newValue,
                                        newValue,
                                        newValue,
                                    ]


#method 7
    def brightness(self, operation):
        displayB = """
[+] increase brightness
[-] decrease brightness
[q] exit
=========================
"""
        limit = -1
        auth = False
        while not auth:
            choice = input(displayB)
            if choice == '+':
                limit = 255
                for h in range (self.height):
                    for w in range (self.width):
                        self.pixelGrid[h][w]=[
                                                min(limit, self.pixelGrid[h][w][0]+25), #red index 0
                                                min(limit, self.pixelGrid[h][w][1]+25), #green index 1
                                                min(limit, self.pixelGrid[h][w][2]+25), #blue index 2
                                            ]
            if choice == '-':
                limit = 0
                for h in range (self.height):
                    for w in range (self.width):
                        self.pixelGrid[h][w]=[
                                                max(limit, self.pixelGrid[h][w][0]-25), #red index 0
                                                max(limit, self.pixelGrid[h][w][1]-25), #green index 1
                                                max(limit, self.pixelGrid[h][w][2]-25), #blue index 2
                                            ]
            elif choice == 'q':
                auth = True
            else:
                print ("Invalid choice, try again")
                auth = False

#method 8
    def contrast (self):
        dC = """
[+] increase contrast
[-] decrease contrsst
[q] exit
=========================
"""
        auth = False
        while not auth:
            choice = input (dC)
            minN=0
            maxN=255
            if choice == '+':
                c= 45
                factor = (259*(c+255))/(255*(259-c))
                for h in range (self.height):
                    for w in range (self.width):
                        newR = int(factor*((self.pixelGrid[h][w][0])-128)+128)
                        newG = int(factor*((self.pixelGrid[h][w][1])-128)+128)
                        newB = int(factor*((self.pixelGrid[h][w][2])-128)+128)
                        newR = minN if newR<minN else maxN if newR>maxN else newR
                        newG = minN if newG<minN else maxN if newG>maxN else newG
                        newB = minN if newB<minN else maxN if newB>maxN else newB
                        self.pixelGrid[h][w]=[
                                                newR,
                                                newG,
                                                newB,
                                            ]
            if choice == '-':
                c= -45
                factor = (259*(c+255))/(255*(259-c))
                for h in range (self.height):
                    for w in range (self.width):
                        newR = int(factor*((self.pixelGrid[h][w][0])-128)+128)
                        newG = int(factor*((self.pixelGrid[h][w][1])-128)+128)
                        newB = int(factor*((self.pixelGrid[h][w][2])-128)+128)
                        newR = minN if newR<minN else maxN if newR>maxN else newR
                        newG = minN if newG<minN else maxN if newG>maxN else newG
                        newB = minN if newB<minN else maxN if newB>maxN else newB
                        self.pixelGrid[h][w]=[
                                                newR,
                                                newG,
                                                newB,
                                            ]
            elif choice == 'q':
                auth = True
                break
            else:
                print ("Invalid choice, try again")
                auth = False


def main():
    menu = """
=========================
Python Image Processor
=========================
a) Invert Colors
b) Flip Image
c) Display color channel
d) Convert to grayscale
e) Adjust brightness
f) Adjust contrast
s) SAVE current image
-------------------------
o) Open new image
q) Quit
=========================
    """
    openmethod = False
    while not openmethod:
        filename = input ("Enter the filename(end in .bmp):")
        myImage = ImageProcesse(filename)
        openmethod = True
        auth = False
        while not auth:
            choice = input(menu)
            if choice == 'a':
                myImage.invert()
                auth = False
            elif choice == 'b':
                myImage.flip(['v','h'])
                auth = False
            elif choice == 'c':
                myImage.displayChannel(['r','g','b'])
                auth = False
            elif choice == 'd':
                myImage.grayscale()
                auth = False
            elif choice == 'e':
                myImage.brightness(['+','-'])
                auth = False
            elif choice == 'f':
                myImage.contrast()
                auth = False
            elif choice == 's':
                text = input("Enter name for edited picture (must be have .bmp extension):")
                myImage.save(text)
                auth = False
            elif choice == 'o':
                auth = True
                openmethod = False
            elif choice == 'q':
                print ("Exisiting program...")
                auth = True
                break
            else:
                print ("Invalid choice, try again.")
                auth = False

main()
