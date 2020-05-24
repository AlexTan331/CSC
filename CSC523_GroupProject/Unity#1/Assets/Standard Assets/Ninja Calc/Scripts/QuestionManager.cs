using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;
public class QuestionManager : MonoBehaviour
{
    public Camera[] cameras;
    public GameObject questions;
    private string ButtonName;

    public TextAsset problemFile;
    public string[] problems;           //each line has a one set of coeffs/expons
    public string[] coeffExponsValues_problems;

    public TextAsset choiceAFile;
    public string[] choiceA;
    public string[] coeffExponsValues_choiceA;

    public TextAsset choiceBFile;
    public string[] choiceB;
    public string[] coeffExponsValues_choiceB;


    //coefficients and exponents
    public TextMesh coeff1_problems;
    public TextMesh firstposition_X_problems;
    public TextMesh expon1_problems;
    public TextMesh coeff2_problems;
    public TextMesh secondposition_X_problems;
    public TextMesh expon2_problems;
    public TextMesh constant_problems;

    //choiceA
    public TextMesh coeff1_choiceA;
    public TextMesh firstposition_X_choiceA;
    public TextMesh expon1_choiceA;
    public TextMesh coeff2_choiceA;
    public TextMesh secondposition_X_choiceA;
    public TextMesh expon2_choiceA;
    public TextMesh constant_choiceA;

    //choiceB
    public TextMesh coeff1_choiceB;
    public TextMesh firstposition_X_choiceB;
    public TextMesh expon1_choiceB;
    public TextMesh coeff2_choiceB;
    public TextMesh secondposition_X_choiceB;
    public TextMesh expon2_choiceB;
    public TextMesh constant_choiceB;


    public int problemSet;

    void Start()
    {
        problemSet = Random.Range(0, 14);

        cameras[0].gameObject.SetActive(true);
        cameras[1].gameObject.SetActive(false);
        questions.gameObject.SetActive(false);

        // each problem is on a separate line
        problems = problemFile.text.Split("\n"[0]);
        choiceA = choiceAFile.text.Split("\n"[0]);
        choiceB = choiceBFile.text.Split("\n"[0]);

        loadCoeffsExpons(problemSet);

    }

    public void checkAnswer()
    {
        ButtonName = EventSystem.current.currentSelectedGameObject.name;
        switch (problemSet)
        {
            case 0:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                break;
            case 1:
                if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                break;
            case 2:
                if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                break;
            case 3:
                if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                break;
            case 4:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                break;
            case 5:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                break;
            case 6:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                break;
            case 7:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                break;
            case 8:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                break;
            case 9:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                break;
            case 10:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                break;
            case 11:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                break;
            case 12:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                break;
            case 13:
                if (ButtonName == "ChoiceA")
                {
                    FindObjectOfType<GameManager>().isWrongAnswer_dynamic();
                }
                else if (ButtonName == "ChoiceB")
                {
                    FindObjectOfType<GameManager>().isCorrectAnswer_dynamic();
                }
                break;
            default:
                break;

        }

        nextQuestion();
    }

    public void nextQuestion()
    {

        if (problemSet < 13)
        {
            problemSet++;
            loadCoeffsExpons(problemSet);
        }
        else if (problemSet == 13)
        {
            problemSet = 0;
            loadCoeffsExpons(problemSet);
        }
    }
    public void loadCoeffsExpons(int problemSet)
    {
        //each set is comma-delimited
        coeffExponsValues_problems = problems[problemSet].Split(","[0]);
        coeffExponsValues_choiceA = choiceA[problemSet].Split(","[0]);
        coeffExponsValues_choiceB = choiceB[problemSet].Split(","[0]);

        coeff1_problems.text = coeffExponsValues_problems[0];
        firstposition_X_problems.text = coeffExponsValues_problems[1];
        expon1_problems.text = coeffExponsValues_problems[2];
        coeff2_problems.text = coeffExponsValues_problems[3];
        secondposition_X_problems.text = coeffExponsValues_problems[4];
        expon2_problems.text = coeffExponsValues_problems[5];
        constant_problems.text = coeffExponsValues_problems[6];

        coeff1_choiceA.text = coeffExponsValues_choiceA[0];
        firstposition_X_choiceA.text = coeffExponsValues_choiceA[1];
        expon1_choiceA.text = coeffExponsValues_choiceA[2];
        coeff2_choiceA.text = coeffExponsValues_choiceA[3];
        secondposition_X_choiceA.text = coeffExponsValues_choiceA[4];
        expon2_choiceA.text = coeffExponsValues_choiceA[5];
        constant_choiceA.text = coeffExponsValues_choiceA[6];

        coeff1_choiceB.text = coeffExponsValues_choiceB[0];
        firstposition_X_choiceB.text = coeffExponsValues_choiceB[1];
        expon1_choiceB.text = coeffExponsValues_choiceB[2];
        coeff2_choiceB.text = coeffExponsValues_choiceB[3];
        secondposition_X_choiceB.text = coeffExponsValues_choiceB[4];
        expon2_choiceB.text = coeffExponsValues_choiceB[5];
        constant_choiceB.text = coeffExponsValues_choiceB[6];
    }


    public void TriggertheQuestion()
    {
        cameras[0].gameObject.SetActive(false);
        cameras[1].gameObject.SetActive(true);
        questions.gameObject.SetActive(true);
        loadCoeffsExpons(problemSet);
    }

    public void disableQuestion_dynamic()
    {
        questions.gameObject.SetActive(false);
        cameras[0].gameObject.SetActive(true);
        cameras[1].gameObject.SetActive(false);
    }



}
