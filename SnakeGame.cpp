#include<iostream>
#include<random>
#include<conio.h>
#include<windows.h>
#include<time.h>

bool gameOver;
const int width=20;
const int height=20;
int x,y,goalX,goalY,score;
int tailX[100];
int tailY[100];
int nTail;

enum direction{STOP=0,LEFT,RIGHT,UP,DOWN};
direction dir;

void setUp();
void draw();
void input();
void logic();

int main()
{
    setUp();
    while(gameOver==false)
    {
        draw();
        input();
        logic();
        Sleep(100);
    }
}


void setUp()
{
    srand(time(NULL));
    gameOver=false;
    dir=STOP;
    x=width/2;
    y=height/2;

    goalX=rand()%width;
    goalY=rand()%height;
    score=0;
}

void input()
{
    if (_kbhit())
    {
        switch(_getch())
        {
        case'a':
            dir=LEFT;
            break;
        case'd':
            dir=RIGHT;
            break;
        case 'w':
            dir=UP;
            break;
        case 's':
            dir=DOWN;
            break;
        case 'x':
            gameOver=true;
            break;
        }
    }
}

void logic()
{
    int prevX=tailX[0];
    int prevY=tailY[0];
    int prev2X;
    int prev2Y;
    tailX[0]=x;
    tailY[0]=y;

    for (int i=1;i<nTail;++i)
    {
        prev2X=tailX[i];
        prev2Y=tailY[i];
        tailX[i]=prevX;
        tailY[i]=prevY;
        prevX=prev2X;
        prevY=prev2Y;
    }

    switch (dir)
    {
        case LEFT:
            x--;
            break;
        case RIGHT:
            x++;
            break;
        case UP:
            y--;
            break;
        case DOWN:
            y++;
            break;
        default:
            break;
    }

    if (x>width || x<0 || y>height || y<0)
    {
        gameOver=true;
    }
    for (int i=0;i<nTail;++i)
    {
        if (tailX[i]==x&&tailY[i]==y)
        {
            gameOver=true;
        }
    }

    if(x==goalX && y==goalY)
    {
        ++nTail;
        score=score+10;
        goalX=rand()%width;
        goalY=rand()%height;
    }
}

void draw()
{
    system("cls");
    for (int i=0;i<width+2;++i)
    {
        std::cout<<"#";
    }
    std::cout<<std::endl;

    for (int i=0;i<height;++i)
    {
        for (int j=0;j<width;++j)
        {

            if (j==0)
            {
                std::cout<<"#";
            }
            if(i==y && j==x)
            {
                std::cout<<"O";
            }
            else if(i==goalY && j==goalX)
            {
                std::cout<<"@";
            }
            else
            {
                bool print=false;
                for (int k=0;k<nTail;++k)
                {

                    if (tailX[k]==j&& tailY[k]==i)
                    {
                        std::cout<<"o";
                        print=true;
                    }
                 }
                    if (print==false)
                    {
                        std::cout<<" ";
                    }
            }


            if (j==width-1)
            {
                std::cout<<"#";
            }
        }
        std::cout<<std::endl;
    }

    for (int i=0;i<width+2;++i)
    {
        std::cout<<"#";
    }
    std::cout<<std::endl;
    std::cout<<"Score: "<<score<<std::endl;
}
