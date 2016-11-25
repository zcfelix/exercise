require_relative '../lib/game/player.rb'
require_relative '../lib/command/command.rb'
require_relative '../lib/command/response.rb'

describe Player do

  before(:each) do
    @player = Player.new
    @command = Command.new
  end

  it 'should remain in status of wait_for_command when execute non response command' do
    allow(@command).to receive(:execute).with(an_instance_of(Player)) {:wait_for_command}
    @player.execute(@command)
    expect(@player.status).to eq(:wait_for_command)
  end

  it 'should change status to wait_for_response when execute command need response' do
    allow(@command).to receive(:execute).with(an_instance_of(Player)) {:wait_for_response}
    @player.execute(@command)
    expect(@player.status).to eq(:wait_for_response)
  end

  it 'should change status to end_turn when receive response' do
    response = Response.new

    allow(@command).to receive(:execute).with(an_instance_of(Player)) {:wait_for_response}
    allow(@command).to receive(:execute_with).with(an_instance_of(Player), an_instance_of(Response)) {:end_turn}
    @player.execute(@command)
    expect(@player.status).to eq(:wait_for_response)
    @player.respond(response)
    expect(@player.status).to eq(:end_turn)
  end

end
