def adjusted_Key (text,key):
    textLength = len(text)
    keyLength = len (key)
    for s in text:
        count = 0
        codeASCID = ord (s)
        if codeASCID not in range (97,123):
            count += 1
        textLength= textLength-count
        different = textLength//keyLength+1
    return ((key * different))[:textLength]

#transfrom text from upper to lower case


def encrypt_vigenere():
    filename = input ("Enter name of file to be encrypted(.txt):")
    rtextF= open (filename,'r')
    etext = rtextF.read()
    #t=input ("Enter some texts:")
    k=input ("Enter the encryption key:")
    text = etext.lower()
    key = k.lower()
    AdjustedKey = adjusted_Key(text,key)
    #print (AdjustedKey)
    i = 0 #text
    j =0 #akey
    result=''
    filename = input ("Enter name for the output file (must be .txt):")
    textF = open (filename,'w')
    while i < len(text):
        if ord(text[i]) in range(97,123):
           code = ord(text[i])
           keycode = ord(AdjustedKey[j])
           #print (j)
           newcode = (code-97+keycode-97)%26+97
           newC=chr(newcode)
           result = result+newC
           #print (len(text))
           #print (len(AdjustedKey))
           j+=1  #move on to the next letter
           i+=1
        else:
            #print(text[i])
            result = result +text[i]
            i+=1
    textF.write(result+"\n")

    rtextF.close()
    textF.close()

#encrypt_vigenere()

def decrypt_vigenere():
    #t=input ("Enter some texts to be decrypted:")
    filename2 = input ("Enter name of file to be decrypted(.txt):")
    rtextF= open (filename2,'r')
    text2 = rtextF.read()
    k=input ("Enter the encryption key:")
    text = text2.lower()
    key = k.lower()
    AdjustedKey = adjusted_Key(text,key)
    #print (AdjustedKey)
    i = 0 #text
    j =0 #akey
    filename = input ("Enter name for the output file (must be .txt):")
    dtextF = open (filename,'w')
    result=''
    while i < len(text):
        if ord(text[i]) in range(97,123):
           dCode = ord(text[i])-97
           keycode = ord(AdjustedKey[j])-97
           newcode = (dCode-keycode+26)%26+97
           newC=chr(newcode)
           result = result+newC
           j+=1  #move on to the next letter
           i+=1
        else:
            result = result +text[i]
            i+=1
    dtextF.write(result+"\n")

    rtextF.close()
    dtextF.close()
    #print ("encrypted text:",result)

#decrypt_vigenere()

def menu():

    menu = """
Select Operation:
1) to encrypt text
2) to decrypt text
9) Quit
"""
    choice = -1
    while choice!=0:
        choice = float (input (menu))
        if choice == 1:
            encrypting = encrypt_vigenere()
            print ("DONE!")
        elif choice == 2:
            decrypting = decrypt_vigenere()
            print ("DONE!")
        elif choice == 9:
            break
        else:
            print ("Invalid option. Try again.")

menu()
