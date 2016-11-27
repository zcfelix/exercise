require_relative '../lib/game/player.rb'
require_relative '../lib/command/roll_command.rb'
require_relative '../lib/game/map.rb'
require_relative '../lib/place/place.rb'
require_relative '../lib/place/prison.rb'
require_relative '../lib/game/config.rb'


describe RollCommand do
  before(:each) do
    @step = 1
    @target = Prison.new
    @map = Map.new
    @roll = RollCommand.new(@map, @step)
    allow(@map).to receive(:move) {@target}
    @player = Player.new
    @player.execute(@roll)
  end

  it 'should player wait for turn when roll to prison' do
    expect(@player.status).to eq(:wait_for_turn)
    expect(@player.paused_turns).to eq(PrisonConf::PAUSED_TURNS)

    waited_turns = 0
    until @player.start_turn == :wait_for_command
      waited_turns += 1
    end
    expect(waited_turns).to eq(PrisonConf::PAUSED_TURNS)
    expect(@player.start_turn).to eq(:wait_for_command)
  end

end
