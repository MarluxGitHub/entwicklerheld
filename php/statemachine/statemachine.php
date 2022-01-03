<?php

declare(strict_types=1);

namespace entwicklerheld;

class State
{
    public const INCOMPLETE = "INCOMPLETE";
    public const NEW_STATE = "NEW";
    public const IN_REVIEW = "IN_REVIEW";
    public const READY_FOR_TRANSMISSION = "READY_FOR_TRANSMISSION";
    public const TRANSMITTED = "TRANSMITTED";
    public const ERROR = "ERROR";
    public const DUPLICATE_FAKE = "DUPLICATE_FAKE";
    public const SUCCESSFUL = "SUCCESSFUL";
    public const INACTIVE = "INACTIVE";
    public const CANCELLED = "CANCELED";
    public const NOT_SUCCESSFUL = "NOT_SUCCESSFUL";

    private $name;
    private $transitions;

    public function __construct(string $name, array $transitions)
    {
        $this->name = $name;
        $this->transitions = $transitions;
    }

    public function getName(): string
    {
        return $this->name;
    }

    public function getTransitions(): array
    {
        return $this->transitions;
    }
}

class StateMachine
{
    public static function getStateMachine(): array
    {
        return [
            State::INCOMPLETE => new State(State::INCOMPLETE, [State::DUPLICATE_FAKE, State::NEW_STATE, STATE::ERROR]),
            State::NEW_STATE => new State(State::NEW_STATE, [State::DUPLICATE_FAKE, State::IN_REVIEW, State::ERROR]),
            State::IN_REVIEW => new State(State::IN_REVIEW, [State::DUPLICATE_FAKE, State::READY_FOR_TRANSMISSION, State::ERROR]),
            State::READY_FOR_TRANSMISSION => new State(State::READY_FOR_TRANSMISSION, [State::TRANSMITTED, State::ERROR]),
            State::TRANSMITTED => new State(State::TRANSMITTED, [State::NOT_SUCCESSFUL, State::SUCCESSFUL, State::ERROR]),
            State::ERROR => new State(State::ERROR, []),
            State::DUPLICATE_FAKE => new State(State::DUPLICATE_FAKE, []),
            State::SUCCESSFUL => new State(State::SUCCESSFUL, [State::INACTIVE, State::CANCELLED, State::ERROR]),
            State::INACTIVE => new State(State::INACTIVE, []),
            State::CANCELLED => new State(State::CANCELLED, []),
            State::NOT_SUCCESSFUL => new State(State::NOT_SUCCESSFUL, []),
        ];
    }
}


class PolicyRequest extends AbstractPolicyRequest implements IPolicyRequest
{
    private $stateMachine;
    public $transitionState;

    public function __construct(string $state)
    {
        $this->stateMachine = StateMachine::getStateMachine();
        $this->transitionState = $this->stateMachine[$state];
    }

    public function getLeadState(): string
    {
        return $this->transitionState->getName();
    }

    public function getPossibleNextStates(): array
    {
        return $this->transitionState->getTransitions();
    }

    public function transitionState(string $to): bool
    {
        $ret = in_array($to, $this->transitionState->getTransitions());

        if (
            $ret
            && $this->currentState == State::INCOMPLETE
            && $to == State::NEW_STATE
            && $this->checkIban($this->getIBAN())
        ) {
        }

        if ($ret) {
            $this->transitionState = $this->stateMachine[$to];
        }
        return $ret;
    }

    public function checkIban(string $iban): bool
    {
        $iban = substr($iban, 4) . substr($iban, 0, 4);
        $iban = $this->replaceLetterWithNumber($iban);
        return bcmod($iban, "97") === "9";
    }

    public function replaceLetterWithNumber(string $string): string
    {
        return preg_replace_callback(['/[A-Z]/', '/^[0]+/'], function ($matches) {
            if (substr($matches[0], 0, 1) !== '0') { // may be multiple leading 0's
                return base_convert($matches[0], 36, 10);
            }
            return '';
        }, $string);
    }
}
